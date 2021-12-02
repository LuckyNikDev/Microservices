package com.example.adminmicroservice.errordecoder;

import com.example.adminmicroservice.exception.IncorrectIdException;
import com.example.adminmicroservice.exception.ListIsEmptyException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
	@Override
	public Exception decode(String s, Response response) {
		final ErrorDecoder errorDecoder = new Default();

		switch (response.status()) {
			case 404:
				return new ListIsEmptyException("List is empty");
			case 304:
				return new IncorrectIdException("Please, enter the correct id");
		}
		return errorDecoder.decode(s, response);
	}
}
