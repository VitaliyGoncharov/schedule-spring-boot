package com.vitgon.schedule.util;

import java.util.List;
import java.util.stream.Collectors;

import com.vitgon.schedule.dto.TeacherDTO;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.auth.User;
import com.vitgon.schedule.model.translation.UserTranslation;

public class UserUtil {
	
	/**
	 * Make up user name | Combine firstname, lastname and middlename
	 * Will return ex.:"Jordan Daniel"
	 * 
	 * @return 
	 */
	public static String makeupUsername(User user, Locale locale) {
		if (user == null) {
			return null;
		}
		
		List<UserTranslation> teacherTransl = user.getTranslations();
		
		return teacherTransl.stream()
			.filter(x -> x.getUserTranslationId().getLocale() == locale)
			.map(x -> {
				String lastname = x.getLastname();
				lastname = lastname.substring(0,1).toUpperCase() + lastname.substring(1);
				
				String firstname = x.getFirstname();
				firstname = firstname.substring(0,1).toUpperCase() + firstname.substring(1);
				
				String middlename = x.getMiddlename();
				middlename = middlename.substring(0,1).toUpperCase() + middlename.substring(1);
				
				return new StringBuilder()
						.append(lastname)
						.append(" ")
						.append(firstname)
						.append(" ")
						.append(middlename)
						.toString();
			})
			.findFirst()
			.orElse(makeupUsername(user));
	}
	
	public static String makeupUsername(User user) {
		if (user == null) {
			return null;
		}
		
		String lastname = user.getKeyLastname();
		lastname = lastname.substring(0,1).toUpperCase() + lastname.substring(1);
		
		String firstname = user.getKeyFirstname();
		firstname = firstname.substring(0,1).toUpperCase() + firstname.substring(1);
		
		String middlename = user.getKeyMiddlename();
		if (middlename != null) {
			middlename = middlename.substring(0,1).toUpperCase() + middlename.substring(1);
		}
		
		StringBuilder nameStringBuilder = new StringBuilder()
				.append(lastname)
				.append(" ")
				.append(firstname);
		
		if (middlename != null) {
			nameStringBuilder
				.append(" ")
				.append(middlename);
		}

		return nameStringBuilder.toString();
	}
	
	public static List<TeacherDTO> mapToTeacherDTOList(List<User> users) {
		return users.stream()
			.map(user -> {
				TeacherDTO teacherDTO = new TeacherDTO();
				teacherDTO.setId(user.getId());
				teacherDTO.setName(UserUtil.makeupUsername(user));
				return teacherDTO;
			})
			.collect(Collectors.toList());
	}
}
