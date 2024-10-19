package com.omegaeducacional.caixa.controller;

import com.omegaeducacional.caixa.domain.movimentation.Movimentation;
import com.omegaeducacional.caixa.domain.movimentation.MovimentationDTO;
import com.omegaeducacional.caixa.service.CashDeskService;
import com.omegaeducacional.caixa.service.MovimentationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/movimentation")
public class MovimentationController {

    @Autowired
    private MovimentationService movimentationService;

    @Autowired
    private CashDeskService cashDeskService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Movimentation>> findAll() {
        return ResponseEntity.ok(movimentationService.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Movimentation> findById(@PathVariable Long id) {
        return ResponseEntity.ok(movimentationService.findById(id));

    }

    @GetMapping("/find/date/{date}")
    public ResponseEntity<List<Movimentation>> findByDate(@RequestParam Date date) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/find/cash-desk/{id}")
    public ResponseEntity<List<Movimentation>> findByCashDesk(@PathVariable Long id) {
      return ResponseEntity.ok(movimentationService.findByCashId(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Movimentation>> filterByDate(@RequestParam(required = true, name = "year") int year,
                                                            @RequestParam(required = true, name = "month") int month,
                                                            @RequestParam(required = true, name = "caixa") Long cashDeskId) {
        return ResponseEntity.ok(movimentationService.filterByDateAndCashDesk(year, month, cashDeskId));
    }

    @PostMapping("/create")
    public ResponseEntity<Movimentation> createMovimentation(@RequestBody @Valid MovimentationDTO data) throws ParseException {
        return ResponseEntity.ok(movimentationService.createMovimentation(data));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMovimentation(@PathVariable Long id) {
        movimentationService.deleteMovimentation(id);
        return ResponseEntity.ok().build();
    }
}
