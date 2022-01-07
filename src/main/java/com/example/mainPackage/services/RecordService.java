package com.example.mainPackage.services;


import com.example.mainPackage.customRequests.AddItemRequest;
import com.example.mainPackage.customRequests.DeleteItemRequest;
import com.example.mainPackage.customRequests.GetItemRequest;
import com.example.mainPackage.customRequests.UpdateItemRequest;
import com.example.mainPackage.customResponses.AddItemResponse;
import com.example.mainPackage.customResponses.ItemResponse;
import com.example.mainPackage.entites.Record;
import com.example.mainPackage.entites.Users;
import com.example.mainPackage.repositories.KeyRepository;
import com.example.mainPackage.repositories.RecordRepository;
import com.example.mainPackage.repositories.ServerKeysRepository;
import com.example.mainPackage.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RecordService {

    RecordRepository recordRepository;
    UserRepository userRepository;
    ServerKeysRepository serverKeysRepository;
    KeyRepository keyRepository;
    UserService userService;
    KeysService keysService;

    @Autowired
    public RecordService(RecordRepository recordRepository, UserRepository userRepository, ServerKeysRepository serverKeysRepository, KeyRepository keyRepository, UserService userService, KeysService keysService) {
        this.recordRepository = recordRepository;
        this.userRepository = userRepository;
        this.serverKeysRepository = serverKeysRepository;
        this.keyRepository = keyRepository;
        this.userService = userService;
        this.keysService = keysService;
    }

    public boolean removeItem(DeleteItemRequest request) {

        recordRepository.deleteById(request.ItemId);

        return true;

    }

    public AddItemResponse addItem(AddItemRequest request) {

        if (userRepository.existsById(request.userId)) {

            Users user = userRepository.findById(request.userId).get();

            Record record = new Record();

            record.setDescription(request.getDescription());

            record.setEmail(request.getEmail());

            record.setPassword(request.getPassword());

            record.setUser(user);

            record.setTitle(request.getTitle());

            record = recordRepository.save(record);

            AddItemResponse response = new AddItemResponse(record.getId(), record.getTitle()
                    , record.getEmail(), record.getPassword(), record.getDescription(), "add item is done");

//            response.encrypt(keysService.getUserKey(request.userId));
            return response;


        } else {


            return null;


        }

    }

    public List<ItemResponse> getAllitems(Long userId) {

        Users user = userRepository.findById(userId).get();
        List<ItemResponse> items = new ArrayList<>();
        List<Record> list = recordRepository.findAllByUser(user);


        for (int i = 0; i < list.size(); i++) {
            Record record = list.get(i);
//            items.add(new ItemResponse(record.getId(), record.getTitle(), record.getEmail(), record.getPassword(), record.getDescription()).encrypt(keysService.getUserKey(userId)));
            items.add(new ItemResponse(record.getId(), record.getTitle(), record.getEmail(), record.getPassword(), record.getDescription()));
        }


        return items;


    }

    public ItemResponse getItem(GetItemRequest request) {


        if (recordRepository.existsById(request.getItemId())) {

            Record record = recordRepository.findById(request.getItemId()).get();

//            return new ItemResponse(record.getId(), record.getTitle(), record.getEmail(), record.getPassword(), record.getDescription()).encrypt(keysService.getUserKey(request.userId));
            return new ItemResponse(record.getId(), record.getTitle(), record.getEmail(), record.getPassword(), record.getDescription());


        }


        return null;


    }


    public ItemResponse updateItem(UpdateItemRequest request) {


        if (recordRepository.existsById(request.getItemId())) {

            Record record = recordRepository.findById(request.getItemId()).get();

            record.setTitle(request.getTitle());

            record.setPassword(request.getPassword());

            record.setEmail(request.getEmail());

            record.setDescription(request.getDescription());


//            return new ItemResponse(record).encrypt(keysService.getUserKey(request.userId));
            return new ItemResponse(record);
        }
        return null;

    }
}
