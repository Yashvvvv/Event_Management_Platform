package com.fullstack.tickets.domain.dtos;

import com.fullstack.tickets.domain.entities.TicketStatusEnum;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A data transfer object representing the response for retrieving ticket details.
 * This class contains information about the ticket, its associated event, and its status.
 * It is used to encapsulate and transfer ticket-related data between different parts
 * of the system or to external applications.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTicketResponseDto {
  private UUID id;
  private TicketStatusEnum status;
  private Double price;
  private String description;
  private String eventName;
  private String eventVenue;
  private LocalDateTime eventStart;
  private LocalDateTime eventEnd;
}
