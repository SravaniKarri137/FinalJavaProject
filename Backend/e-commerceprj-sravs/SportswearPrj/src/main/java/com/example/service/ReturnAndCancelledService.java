package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.ReturnAndCancelled;
import com.example.repository.ReturnAndCancelledRepository;

@Service
public class ReturnAndCancelledService {

    @Autowired
    private ReturnAndCancelledRepository returnRepo;

    public List<ReturnAndCancelled> getAllReturnAndCancelledItems() {
        return returnRepo.findAll(); // ✅ Correct usage
    }
}
