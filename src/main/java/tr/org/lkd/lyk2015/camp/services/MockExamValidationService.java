package tr.org.lkd.lyk2015.camp.services;

import org.springframework.stereotype.Service;

@Service
public class MockExamValidationService implements ExamValidationService {

	@Override
	public boolean validate(Long tckn, String email) {
		if (email.equals("deneme@gmail.com"))
			return true;
		return false;
	}

}
