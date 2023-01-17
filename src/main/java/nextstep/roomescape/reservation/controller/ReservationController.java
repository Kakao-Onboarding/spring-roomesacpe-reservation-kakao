package nextstep.roomescape.reservation.controller;

import nextstep.roomescape.reservation.service.ReservationService;
import nextstep.roomescape.reservation.controller.dto.ReservationResponseDTO;
import nextstep.roomescape.reservation.repository.model.Reservation;
import nextstep.roomescape.reservation.controller.dto.ReservationRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @PostMapping
    public ResponseEntity<Long> createReservation(@RequestBody ReservationRequestDTO reservation) {
        Long id = reservationService.create(reservation);
        return ResponseEntity.created(URI.create("/reservations/" + id)).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> findReservation(@PathVariable Long id) {
        ReservationResponseDTO reservation = reservationService.findById(id);
        return ResponseEntity.ok().body(reservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reservation> deleteReservation(@PathVariable Long id) {
        reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @ExceptionHandler
    public ResponseEntity<String> handle(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
