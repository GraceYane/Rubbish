package com.example.shizhan.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shizhan.reggie.entity.AddressBook;
import com.example.shizhan.reggie.mapper.AddressBookMapper;
import com.example.shizhan.reggie.service.Interface.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {

}
