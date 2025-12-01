package com.fullstack.tickets.mappers;

import com.fullstack.tickets.domain.CreateEventRequest;
import com.fullstack.tickets.domain.CreateTicketTypeRequest;
import com.fullstack.tickets.domain.UpdateEventRequest;
import com.fullstack.tickets.domain.UpdateTicketTypeRequest;
import com.fullstack.tickets.domain.dtos.CreateEventRequestDto;
import com.fullstack.tickets.domain.dtos.CreateEventResponseDto;
import com.fullstack.tickets.domain.dtos.CreateTicketTypeRequestDto;
import com.fullstack.tickets.domain.dtos.GetEventDetailsResponseDto;
import com.fullstack.tickets.domain.dtos.GetEventDetailsTicketTypesResponseDto;
import com.fullstack.tickets.domain.dtos.GetPublishedEventDetailsResponseDto;
import com.fullstack.tickets.domain.dtos.GetPublishedEventDetailsTicketTypesResponseDto;
import com.fullstack.tickets.domain.dtos.ListEventResponseDto;
import com.fullstack.tickets.domain.dtos.ListEventTicketTypeResponseDto;
import com.fullstack.tickets.domain.dtos.ListPublishedEventResponseDto;
import com.fullstack.tickets.domain.dtos.UpdateEventRequestDto;
import com.fullstack.tickets.domain.dtos.UpdateEventResponseDto;
import com.fullstack.tickets.domain.dtos.UpdateTicketTypeRequestDto;
import com.fullstack.tickets.domain.dtos.UpdateTicketTypeResponseDto;
import com.fullstack.tickets.domain.entities.Event;
import com.fullstack.tickets.domain.entities.TicketType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

  CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);

  CreateEventRequest fromDto(CreateEventRequestDto dto);

  CreateEventResponseDto toDto(Event event);

  ListEventTicketTypeResponseDto toDto(TicketType ticketType);

  ListEventResponseDto toListEventResponseDto(Event event);

  GetEventDetailsTicketTypesResponseDto toGetEventDetailsTicketTypesResponseDto(
      TicketType ticketType);

  GetEventDetailsResponseDto toGetEventDetailsResponseDto(Event event);

  UpdateTicketTypeRequest fromDto(UpdateTicketTypeRequestDto dto);

  UpdateEventRequest fromDto(UpdateEventRequestDto dto);

  UpdateTicketTypeResponseDto toUpdateTicketTypeResponseDto(TicketType ticketType);

  UpdateEventResponseDto toUpdateEventResponseDto(Event event);

  ListPublishedEventResponseDto toListPublishedEventResponseDto(Event event);

  GetPublishedEventDetailsTicketTypesResponseDto toGetPublishedEventDetailsTicketTypesResponseDto(
      TicketType ticketType);

  GetPublishedEventDetailsResponseDto toGetPublishedEventDetailsResponseDto(Event event);
}
