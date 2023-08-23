package com.example.hospital.controllers;

import com.example.hospital.dto.user.request.EmployRequestDto;
import com.example.hospital.dto.user.request.UpdateEmployRequestDto;
import com.example.hospital.dto.user.response.DepartmentResponseDto;
import com.example.hospital.dto.user.response.EmployResponstDto;
import com.example.hospital.exceptions.ThenComeUpWithException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/head_doctor")
public class HeadDoctorController {

    @GetMapping("/get_all_department")
    public ResponseEntity<List<DepartmentResponseDto>> getAllDepartment(){
        throw new ThenComeUpWithException("Эндпоинт который должен вернуть список отделений");
    }

    @PostMapping("/new_enploy")
    public ResponseEntity<EmployResponstDto> registerNewEmploy(@RequestBody EmployRequestDto requestDto ){
        throw new ThenComeUpWithException("Контроллер который регистрирует нового работника");
    }

    @GetMapping("/get_all_Employ")
    public ResponseEntity<List<EmployResponstDto>> getAllEmploy(){
        throw new ThenComeUpWithException("Возврощение всех сотрудников");
    }

    @PutMapping("/update_employ")
    public ResponseEntity<EmployResponstDto> updateEmployer(@RequestBody UpdateEmployRequestDto updateEmployRequestDto){
        throw new ThenComeUpWithException("Изменение данных сотрудника");
    }

    @DeleteMapping("/delete_employ")
    public ResponseEntity<String> deleteEmploy(@RequestParam("employId") Long employId){
        throw new ThenComeUpWithException("Удоление сотрудника по id");
    }

    @GetMapping("/report")
    public ResponseEntity<String> getReport(){
        throw new ThenComeUpWithException("формирование отчета");
    }

    @GetMapping("/edict")
    public ResponseEntity<String> setEdict(@RequestParam("edict") String edict){
        throw new ThenComeUpWithException("Указ для больницы");
    }
}
