package tr.org.lkd.lyk2015.camp.services;

import org.springframework.stereotype.Service;

@Service
public interface BlackListValidationService {

	boolean emailControl(Long tckn, String email, String name, String surname);

}
