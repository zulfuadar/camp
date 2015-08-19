package tr.org.lkd.lyk2015.camp.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class MockBlackListValidationService implements BlackListValidationService {

	@Override
	public boolean emailControl(Long tckn, String email, String name, String surname) {
		if (email.equals("deneme@mail.com")) {
			return false;
		}

		return true;
	}

}
