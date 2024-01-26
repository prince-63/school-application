package com.learn.service;

import com.learn.constants.YourSchoolConstants;
import com.learn.model.Contact;
import com.learn.repository.ContactRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public void saveMessage(Contact contact) {
        contact.setStatus(YourSchoolConstants.OPEN);
        contactRepository.save(contact);
    }

    public List<Contact> findMsgWithOpenStatus() {
        return contactRepository.findByStatus(YourSchoolConstants.OPEN);
    }

    public void updateMsgStatus(int contactId) {
        Optional<Contact> contact = contactRepository.findById(contactId);
        contact.ifPresent(contact1 -> {
            contact1.setStatus(YourSchoolConstants.CLOSE);
        });
        contactRepository.save(contact.get());
    }
}
