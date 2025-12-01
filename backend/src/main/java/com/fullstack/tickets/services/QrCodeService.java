package com.fullstack.tickets.services;

import com.fullstack.tickets.domain.entities.QrCode;
import com.fullstack.tickets.domain.entities.Ticket;
import java.util.UUID;

public interface QrCodeService {

  QrCode generateQrCode(Ticket ticket);

  byte[] getQrCodeImageForUserAndTicket(UUID userId, UUID ticketId);
}
