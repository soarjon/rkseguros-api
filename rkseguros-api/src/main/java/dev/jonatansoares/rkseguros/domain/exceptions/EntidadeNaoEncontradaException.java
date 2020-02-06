package dev.jonatansoares.rkseguros.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntidadeNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = -7532667026885882406L;
	
	public EntidadeNaoEncontradaException(String msg) {
		super(msg);
	}
}
