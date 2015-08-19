package tr.org.lkd.lyk2015.camp.services;

import org.springframework.stereotype.Service;

@Service
public abstract interface ExamValidationService {

	public abstract boolean validate(Long tckn, String email);
}
