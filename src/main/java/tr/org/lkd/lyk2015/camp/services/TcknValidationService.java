package tr.org.lkd.lyk2015.camp.services;

import org.springframework.stereotype.Service;

@Service
public interface TcknValidationService {

	public abstract boolean validate(String name, String surname, Integer birthDate, Long tckn);
	
}