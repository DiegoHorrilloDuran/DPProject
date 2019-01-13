package converters;

import java.net.URLDecoder;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Admin;

@Component@Transactional
public class StringToAdminConverter implements Converter<String, Admin>{

	@Override
	public Admin convert(final String text) {
		Admin result;
		String parts[];
		
		if(text == null){
			result = null;
		}else {
			try{
				parts= text.split("//|");
				result = new Admin();
				result.setName(URLDecoder.decode(parts[0],"UTF-8"));
				result.setMiddleName(URLDecoder.decode(parts[1],"UTF-8"));
				result.setSurname(URLDecoder.decode(parts[2],"UTF-8"));
				result.setPhoto(URLDecoder.decode(parts[3],"UTF-8"));
				result.setEmail(URLDecoder.decode(parts[4],"UTF-8"));
				result.setPhoneNumber(URLDecoder.decode(parts[5],"UTF-8"));
				result.setAddress(URLDecoder.decode(parts[6],"UTF-8"));
				
			}catch(Throwable oops){
				throw new RuntimeException(oops);
			}
		}
		return result;
	}

}
