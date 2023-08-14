package com.example.hospital.service;

import com.example.hospital.dto.user.request.UserRequestDto;

public interface AuthorizeService {

    String logInUser(UserRequestDto requestDto);
    String logInUser();

    boolean passwordValidats(String passwordEntryEndPoint, String passwordHandlesFromSystem);

    boolean passwordValidats(String passwordEntryEndPoint);

    String passwordEncode(String password);
}
