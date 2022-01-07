package com.example.mainPackage.services;

import com.example.mainPackage.cryptography.asymmericCipers.RSA;
import com.example.mainPackage.customRequests.GetUserKeyRequest;
import com.example.mainPackage.entites.SecureKey;
import com.example.mainPackage.entites.ServerKeys;
import com.example.mainPackage.entites.Users;
import com.example.mainPackage.repositories.KeyRepository;
import com.example.mainPackage.repositories.ServerKeysRepository;
import com.example.mainPackage.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.KeyPair;
import java.security.PublicKey;

@Service
@Transactional
public class KeysService {


    UserRepository userRepository;

    ServerKeysRepository serverKeysRepository;

    KeyRepository keyRepository;

    @Autowired
    public KeysService(UserRepository userRepository, ServerKeysRepository serverKeysRepository, KeyRepository keyRepository) {
        this.userRepository = userRepository;
        this.serverKeysRepository = serverKeysRepository;
        this.keyRepository = keyRepository;
    }

    public KeyPair getServerKey() {


        if (serverKeysRepository.findAll().size() == 0) {

            KeyPair keyPair = RSA.generateKeys();

            String publicKey = RSA.encoder(keyPair.getPublic().getEncoded());

            String privateKey = RSA.encoder(keyPair.getPrivate().getEncoded());

            ServerKeys serverKeys = new ServerKeys();

            serverKeys.setPrivateKey(privateKey);

            serverKeys.setPublicKey(publicKey);


            serverKeysRepository.save(serverKeys);


        }


        ServerKeys result = serverKeysRepository.findAll().get(0);


        return RSA.generateKeysFormString(result.getPrivateKey(), result.getPublicKey());


    }


    public PublicKey getUserKey(GetUserKeyRequest request) {

        SecureKey key = null;
        if (userRepository.existsByName(request.getUserName())) {

            Users user = userRepository.findByName(request.getUserName());

            key = keyRepository.findByUser(user);

            System.err.println(key);
            return RSA.generatePublicKeyString(key.getKey());


        }


        return null;
    }

    public PublicKey getUserKey(Long id) {

        SecureKey key = null;
        if (userRepository.existsById(id)) {

            Users user = userRepository.findById(id).get();

            key = keyRepository.findByUser(user);

            System.err.println(key);
            return RSA.generatePublicKeyString(key.getKey());


        }


        return null;
    }
}
