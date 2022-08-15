package com.example.shizhan.rubbish.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shizhan.rubbish.entity.AddressBook;
import com.example.shizhan.rubbish.mapper.AddressBookMapper;
import com.example.shizhan.rubbish.service.Interface.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
