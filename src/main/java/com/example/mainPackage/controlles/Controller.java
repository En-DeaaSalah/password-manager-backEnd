package com.example.mainPackage.controlles;

import com.example.mainPackage.cryptography.asymmericCipers.RSA;
import com.example.mainPackage.customRequests.*;
import com.example.mainPackage.customResponses.*;
import com.example.mainPackage.entites.Users;
import com.example.mainPackage.services.KeysService;
import com.example.mainPackage.services.ProfileService;
import com.example.mainPackage.services.RecordService;
import com.example.mainPackage.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class Controller {


    UserService userService;

    ProfileService profileService;

    RecordService recordService;


    KeysService keysService;

    @Autowired
    public Controller(UserService userService, ProfileService profileService, RecordService recordService, KeysService keysService) {
        this.userService = userService;
        this.profileService = profileService;
        this.recordService = recordService;
        this.keysService = keysService;
    }

    @PostMapping(path = "register", consumes = "application/json", produces = "application/json")
    RegistrationResponse register(@RequestBody RegistrationRequest request) {


//
//        if (request.verifySign(RSA.generatePublicKeyString(request.getPublicKey()))) {
//
//
//            System.err.println("User Signature is true ");
//
//        }


        request = request.decrypt(keysService.getServerKey().getPrivate());


        RegistrationResponse response = new RegistrationResponse();


        Users user = userService.userRegistration(request);


        if (user == null) {
            response.message = "try again";
            response.user = null;
            return response;
        }

        response.message = "registration is done";
        response.user = user;

        return response;


    }

    @PostMapping(path = "addItem", consumes = "application/json", produces = "application/json")
    AddItemResponse addItem(@RequestBody AddItemRequest request) {


//        if (request.verifySign(keysService.getUserKey(request.getUserId()))) {
//
//
//            System.err.println("User Signature is true ");
//
//        }

        request = request.decrypt(keysService.getServerKey().getPrivate());

        return recordService.addItem(request);

    }

    @PostMapping(path = "removeItem", consumes = "application/json", produces = "application/json")
    String removeItem(@RequestBody DeleteItemRequest request) {


        System.err.println(request);

//        request=request.decrypt(keysService.getServerKey().getPrivate());


        System.err.println(recordService.removeItem(request));


        return "server message";
    }


    @PostMapping(path = "getUserKey", consumes = "application/json", produces = "application/json")
    String getUserKey(@RequestBody GetUserKeyRequest request) {


//        if (request.verifySign(keysService.getUserKey(request.getUserId()))) {
//
//
//            System.err.println("User Signature is true ");
//
//        }

//        request = request.decrypt(keysService.getServerKey().getPrivate());


        System.err.println(request);
        return RSA.encoder(keysService.getUserKey(request).getEncoded());
    }


    @PostMapping(path = "shareItem", consumes = "application/json", produces = "application/json")
    String sharedItem(@RequestBody SharedItemRequest request) {

//        if (request.verifySign(keysService.getUserKey(request.getUserId()))) {
//
//
//            System.err.println("User Signature is true ");
//
//        }
//
//        System.err.println(request);

        request = request.decrypt(keysService.getServerKey().getPrivate());

        System.err.println(request);
        return userService.sharedItem(request);


    }


    @PostMapping(path = "orderProcess", consumes = "application/json", produces = "application/json")
    String orderProcess(@RequestBody OrderAnswerRequest request) {

//        System.err.println(request);
//
//
//        if (request.verifySign(keysService.getUserKey(request.getUserId()))) {
//
//
//            System.err.println("User Signature is true ");
//
//        }

        request = request.decrypt(keysService.getServerKey().getPrivate());


        return userService.orderProcess(request);
    }

    @PostMapping(path = "getSharedOrder", consumes = "application/json", produces = "application/json")
    List<SharedOrderResponce> getSharedOrder(@RequestBody GetSharedOrderRequest request) {


        return userService.getSharedOrder(request);


    }


    @PostMapping(path = "updateItem", consumes = "application/json", produces = "application/json")
    ItemResponse updateItem(@RequestBody UpdateItemRequest request) {


//        if (request.verifySign(keysService.getUserKey(request.getUserId()))) {
//
//
//            System.err.println("User Signature is true ");
//
//        }
//
//
        request = request.decrypt(keysService.getServerKey().getPrivate());


        return recordService.updateItem(request);
    }


    @PostMapping(path = "getItem", consumes = "application/json", produces = "application/json")
    ItemResponse getItem(@RequestBody GetItemRequest request) {


        return recordService.getItem(request);
    }

    @PostMapping(path = "getAllItems", consumes = "application/json", produces = "application/json")
    List<ItemResponse> getAllItems(@RequestBody GetAllItemRequest request) {


        return recordService.getAllitems(request.userId);
    }


    @GetMapping(path = "getServerKey")
    String getServerKey() {

        return RSA.encoder(keysService.getServerKey().getPublic().getEncoded());
    }


    @PostMapping(path = "login", consumes = "application/json", produces = "application/json")
    String login(@RequestBody LoginRequest request) {
        System.err.println(request);

        if (userService.loginUser(request)) {

            return "Login Ok";
        }

        return "Login in not ok";

    }

    @PostMapping(path = "logout", consumes = "application/json", produces = "application/json")
    String logout(@RequestBody LogOutRequest request) {
        System.err.println(request);

        if (userService.logOutUser(request)) {

            return "logOut Ok";
        }

        return "logOut in not ok";


    }

    @PostMapping(path = "getUsersName", consumes = "application/json", produces = "application/json")
    List<String> getUserNames(@RequestBody GetUsersNameRequest request) {


        return userService.getAllUsersNames(request.userId);


    }

}
