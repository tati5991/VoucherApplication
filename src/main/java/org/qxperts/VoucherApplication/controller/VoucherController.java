package org.qxperts.VoucherApplication.controller;

import org.qxperts.VoucherApplication.entities.CartDetails;
import org.qxperts.VoucherApplication.entities.Voucher;
import org.qxperts.VoucherApplication.enums.Messages;
import org.qxperts.VoucherApplication.exceptions.ValidationExceptionErrors;
import org.qxperts.VoucherApplication.message.ValidationMessage;
import org.qxperts.VoucherApplication.services.VoucherGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    private VoucherGeneratorService voucherGeneratorService;

    @PostMapping(path = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ValidationMessage> createNewVoucher(@Valid @RequestBody Voucher newVoucher) {
        try {
            Voucher voucher = voucherGeneratorService.save(newVoucher);
            return new ResponseEntity<>(new ValidationMessage(Messages.VOUCHER_CREATED.getMessageText(), voucher), HttpStatus.OK);
        } catch (ValidationExceptionErrors e) {
            return new ResponseEntity<>(new ValidationMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/validate",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ValidationMessage> validateVoucher(@RequestParam(name = "voucherCode") String code) {
        try {
            voucherGeneratorService.validateVoucher(code);
            return new ResponseEntity<>(new ValidationMessage(Messages.VOUCHER_IS_VALID.getMessageText()), HttpStatus.OK);
        } catch (ValidationExceptionErrors e) {
            return new ResponseEntity<>(new ValidationMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/apply",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ValidationMessage> applyVoucher(@RequestParam(name = "voucherCode") String code, @RequestBody CartDetails cartDetails) {
        try {
            voucherGeneratorService.applyVoucher(code, cartDetails);
            return new ResponseEntity<>(new ValidationMessage(Messages.VOUCHER_IS_APPLIED.getMessageText(), cartDetails), HttpStatus.OK);
        } catch (ValidationExceptionErrors e) {
            return new ResponseEntity<>(new ValidationMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
