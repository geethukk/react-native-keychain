package com.dreamorbit.keychain.cipherStorage;

import androidx.annotation.NonNull;

import com.dreamorbit.keychain.SecurityLevel;
import com.dreamorbit.keychain.exceptions.CryptoFailedException;
import com.dreamorbit.keychain.exceptions.KeyStoreAccessException;

public interface CipherStorage {
    abstract class CipherResult<T> {
        public final T key;
        public final T value;

        public CipherResult(T key, T value) {
            this.key = key;
            this.value = value;
        }
    }

    class EncryptionResult extends CipherResult<byte[]> {
        public CipherStorage cipherStorage;

        public EncryptionResult(byte[] key, byte[] value, CipherStorage cipherStorage) {
            super(key, value);
            this.cipherStorage = cipherStorage;
        }
    }

    class DecryptionResult extends CipherResult<String> {
      private SecurityLevel securityLevel;

      public DecryptionResult(String key, String value, SecurityLevel level) {
            super(key, value);
            securityLevel = level;
        }

      public SecurityLevel getSecurityLevel() {
        return securityLevel;
      }
    }

    EncryptionResult encrypt(@NonNull String service, @NonNull String key, @NonNull String value, SecurityLevel level) throws CryptoFailedException;

    DecryptionResult decrypt(@NonNull String service, @NonNull byte[] key, @NonNull byte[] value) throws CryptoFailedException;

    void removeKey(@NonNull String service) throws KeyStoreAccessException;

    String getCipherStorageName();

    int getMinSupportedApiLevel();

    SecurityLevel securityLevel();

    boolean supportsSecureHardware();
}
