package com.neuedatraining.CreditCardApplication.service;
import com.neuedatraining.CreditCardApplication.dto.UserPerPageResponse;
import com.neuedatraining.CreditCardApplication.entity.CreditCardUser;
import com.neuedatraining.CreditCardApplication.exception.CardUserAlreadyFoundException;
import com.neuedatraining.CreditCardApplication.exception.CardUserNotFoundException;
import com.neuedatraining.CreditCardApplication.repository.CreditCardUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class CardUserService {
    @Autowired
    private CreditCardUserRepo repo;

    public long countUser() {
        return this.repo.count();
    }

    public List<CreditCardUser> getUsers() {
        return repo.findAll();
    }

    public List<CreditCardUser> findCardUserByCustomerId(int customer_id) throws CardUserNotFoundException {
        return repo.findByCustomerId(customer_id);
    }
    public CreditCardUser findCardUserByName(String fName,String lName){
        return repo.findByName(fName,lName);
    }

    public List<CreditCardUser> findCardUserByGender(String gender) {
        return repo.findByGender(gender);
    }

    public CreditCardUser insertUser(CreditCardUser user) throws CardUserAlreadyFoundException {
        // Calculate the next customer ID by incrementing the count
        int nextCustomerId = (int) this.repo.count() + 1;
        user.setCustomerId(nextCustomerId);
        if (this.repo.existsByCustomerId(user.getCustomerId())) {
            throw new CardUserAlreadyFoundException("Card user with " + user.getCustomerId() + " already exists");
        }

        CreditCardUser cardUser = this.repo.save(user);
        return cardUser;
    }


    public CreditCardUser updateUser( CreditCardUser userToUpdate) throws CardUserNotFoundException {

        if(! repo.existsByCustomerId(userToUpdate.getCustomerId()))
            throw new CardUserNotFoundException("User with "+userToUpdate.getCustomerId()+" does not exist");
        repo.save(userToUpdate);
        return userToUpdate;
    }
    public void deleteUser(int customer_id) throws CardUserNotFoundException {
        if (repo.existsByCustomerId(customer_id)) {
            repo.deleteByCustomerId(customer_id);
        } else {
            throw new CardUserNotFoundException("User with " + customer_id + " does not exist");
        }
    }
    public UserPerPageResponse getUsersByPagination(int pageno, int size) {
        Pageable pageable = PageRequest.of(pageno, size);
        Page<CreditCardUser> page = repo.findAll( pageable);
        int totalPages = page.getTotalPages();
        long totalElements = page.getTotalElements();
        int noofelements = page.getNumberOfElements();
        int pagesize = page.getSize();
        UserPerPageResponse response = new UserPerPageResponse();
        response.setUsers(page.getContent());
        response.setNoofelements(noofelements);
        response.setPagesize(pagesize);
        response.setTotalElements(totalElements);
        response.setTotalPages(totalPages);
        return response;
    }
}
