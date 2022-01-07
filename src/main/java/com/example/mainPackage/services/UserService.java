package com.example.mainPackage.services;

import com.example.mainPackage.cryptography.asymmericCipers.RSA;
import com.example.mainPackage.customRequests.*;
import com.example.mainPackage.customResponses.SharedOrderResponce;
import com.example.mainPackage.entites.*;
import com.example.mainPackage.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {

    UserRepository userRepository;

    KeyRepository keyRepository;

    RecordRepository recordRepository;

    ServerKeysRepository serverKeysRepository;

    ProfileRepository profileRepository;

    ShareOrderRepository shareOrderRepository;

    KeysService keysService;

    @Autowired
    public UserService(UserRepository userRepository, KeyRepository keyRepository, RecordRepository recordRepository, ServerKeysRepository serverKeysRepository, ProfileRepository profileRepository, ShareOrderRepository shareOrderRepository, KeysService keysService) {
        this.userRepository = userRepository;
        this.keyRepository = keyRepository;
        this.recordRepository = recordRepository;
        this.serverKeysRepository = serverKeysRepository;
        this.profileRepository = profileRepository;
        this.shareOrderRepository = shareOrderRepository;
        this.keysService = keysService;
    }

    public Users userRegistration(RegistrationRequest request) {
        Users user = new Users();
        try {

            Profile profile = new Profile();
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            user.setName(request.getUserName());
            user.setProfile(profile);
            SecureKey key = new SecureKey(request.getPublicKey(), user);
            keyRepository.save(key);
            user.addKey(key);
            Users result = userRepository.save(user);
            keyRepository.save(key);
//            profile.setOwner(user);


//            result = result.decrypt(keysService.getServerKey().getPrivate());
//            System.err.println(result);
//            return result.encrypt(keysService.getUserKey(result.getId()));

            return result;


        } catch (Exception e) {

            return null;
        }


    }

    public boolean loginUser(LoginRequest request) {

        if (userRepository.existsById(request.userId)) {

            Users user = userRepository.findById(request.userId).get();

            if (!user.getEmail().equals(request.email) || !user.getPassword().equals(request.password))
                return false;

            user.setLogin(true);


            return true;
        } else {

            return false;
        }

    }

    public boolean logOutUser(LogOutRequest request) {

        if (userRepository.existsById(request.userId)) {

            Users user = userRepository.findById(request.userId).get();

            user.setLogin(false);


            return true;
        } else {

            return false;
        }

    }

    public List<String> getAllUsersNames(Long userId) {


        List<Users> list = userRepository.findAll();
        List<String> names = new ArrayList<String>();
        List<String> names2 = new ArrayList<>();
        for (Users users : list) {

            if (!users.getId().equals(userId)) {


                names.add(users.getName());


            }


        }
        return names;

//        for (int i = 0; i < names.size(); i++) {
//
//            names2.add(RSA.decrypt(names.get(i), keysService.getServerKey().getPrivate()));
//        }
//
//
//        return names2;

    }

    public List<SharedOrderResponce> getSharedOrder(GetSharedOrderRequest request) {

        Users user = userRepository.findById(request.userId).get();

        List<ShareOrder> list = shareOrderRepository.findAllByTargetUser(user);

        List<SharedOrderResponce> result = new ArrayList<>();


        for (int i = 0; i < list.size(); i++) {

            if (recordRepository.existsById(list.get(i).getItem().getId())) {

                Record item = recordRepository.getOne(list.get(i).getItem().getId());
                SharedOrderResponce order = new SharedOrderResponce(item);
                order.setMessage(list.get(i).getMessage());
                result.add(order);


            }


        }


        return result;

    }

    public String sharedItem(SharedItemRequest request) {

        Users target = userRepository.findByName(request.getUserSharedName());
        Users sours = userRepository.getOne(request.getUserId());
        Record record = recordRepository.getOne(request.getItemID());
        ShareOrder order = new ShareOrder(record, sours, target, request.getMessage());
        shareOrderRepository.save(order);

        return "Your Order is saved";

    }

    public String orderProcess(OrderAnswerRequest request) {

        ShareOrder order = shareOrderRepository.getOne(request.getOrderId());

        if (request.getAnswer().equals("1")) {


            Record record = new Record();

            record.updateRecord(order.getItem());

            record.setUser(order.getTargetUser());

            recordRepository.save(record);

            shareOrderRepository.delete(order);

            return "order is Accept ";


        } else {

            shareOrderRepository.delete(order);

            return "order is reject ";
        }

    }
}
