package com.sudo248.invoiceservice.Controller;

import com.sudo248.domain.base.BaseResponse;
import com.sudo248.domain.common.Constants;
import com.sudo248.invoiceservice.Controller.dto.InvoiceAddDto;
import com.sudo248.invoiceservice.Controller.dto.InvoiceDto;
import com.sudo248.invoiceservice.service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/add")
    public ResponseEntity<BaseResponse<?>> addInvoice(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @RequestBody InvoiceAddDto invoiceAddDto
    ) {
        InvoiceAddDto invoiceAddDto1 = invoiceService.addInvoice(userId, invoiceAddDto);
        return BaseResponse.ok(invoiceAddDto1);
    }

    @GetMapping("/user")
    public ResponseEntity<BaseResponse<?>> getInvoiceByUserId(
            @RequestHeader(Constants.HEADER_USER_ID) String userId
    ) {
        List<InvoiceDto> list = invoiceService.getListInvoice(userId);
        return BaseResponse.ok(list);
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<BaseResponse<?>> getInvoiceByInvoiceId(@PathVariable String invoiceId) {
        InvoiceDto invoiceDto = invoiceService.getInvoiceByInvoiceId(invoiceId);
        return BaseResponse.ok(invoiceDto);
    }

    @DeleteMapping("/{invoiceId}")
    public ResponseEntity<BaseResponse<?>> deleteInvoiceByInvoiceId(@PathVariable String invoiceId) {
        boolean res = invoiceService.deleteInvoice(invoiceId);
        if(res == true)
            return BaseResponse.ok(invoiceId);
        return BaseResponse.ok(null);
    }

    @PutMapping("/{invoiceId}")
    public ResponseEntity<BaseResponse<?>> updateInvoice(@RequestBody InvoiceDto invoiceDto, @PathVariable String invoiceId){
        InvoiceDto invoiceDto1 = invoiceService.updateInvoice(invoiceDto, invoiceId);
        return BaseResponse.ok(invoiceDto1);
    }

    @PutMapping("/{invoiceId}/update/{field}/{fieldId}")
    public ResponseEntity<BaseResponse<?>> updateInvoiceByCase(
            @PathVariable(name = "invoiceId") String invoiceId,
            @PathVariable(name = "field") String field,
            @PathVariable(name = "fieldId") String fieldId
    ){
        InvoiceDto invoiceDto = invoiceService.updateInvoiceByField(invoiceId, field, fieldId);
        return BaseResponse.ok(invoiceDto);
    }

    @PutMapping("/{invoiceId}/payment/{paymentId}")
    ResponseEntity<BaseResponse<?>> patchPaymentInvoice(
            @PathVariable("invoiceId") String invoiceId,
            @PathVariable("paymentId") String paymentId
    ) {
        invoiceService.updateInvoicePayment(invoiceId, paymentId);
        return BaseResponse.ok(true);
    }
}