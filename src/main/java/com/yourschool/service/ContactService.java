package com.yourschool.service;

import com.yourschool.config.YourSchoolProperties;
import com.yourschool.constants.YourSchoolConstants;
import com.yourschool.model.Contact;
import com.yourschool.repository.ContactRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private YourSchoolProperties yourSchoolProperties;

    public void saveMessage(Contact contact) {
        contact.setStatus(YourSchoolConstants.OPEN);
        contactRepository.save(contact);
    }

    public Page<Contact> findMsgWithOpenStatus(int pageNum, String sortField, String sortDir) {
        int pageSize = yourSchoolProperties.getPageSize();
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sortDir.equals("asc") ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending());
        Page<Contact> pageListMessages = contactRepository.findByStatus(YourSchoolConstants.OPEN, pageable);
        return pageListMessages;
    }

    public void updateMsgStatus(int contactId) {
        Optional<Contact> contact = contactRepository.findById(contactId);
        contact.ifPresent(contact1 -> {
            contact1.setStatus(YourSchoolConstants.CLOSE);
        });
        contactRepository.save(contact.get());
    }

}
