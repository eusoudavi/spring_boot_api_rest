package br.com.alura.forum.config.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroValidationHandler {
//    CLASSE PARA VALIDAÇÃO DE ERRO DAS REQUISIÇÕES - VALIDAÇÃO DE FORMULÁRIO

    @Autowired
    private MessageSource messageSource;
//    CLASSE PARA CRIAR AS MENSAGENS DE ERRO CONFORME A LOCALIZAÇÃO DO CLIENTE

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)  // COMO ESSA CLASSE SERVE PARA VALIDAR EXCEPTIONS, SE NÃO PEDIR PARA PASSAR O CÓDIGO 400, O SPRING VAI DEVOLVER HTTP 200
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroFormularioDto> handle(MethodArgumentNotValidException exception){
        List<ErroFormularioDto> dto = new ArrayList<ErroFormularioDto>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach(e -> {
            String campo = e.getField();
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroFormularioDto erro = new ErroFormularioDto(campo, mensagem);
            dto.add(erro);
        });

        return dto;
    }

}
