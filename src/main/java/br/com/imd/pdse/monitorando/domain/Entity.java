package br.com.imd.pdse.monitorando.domain;

import java.time.Instant;
import java.util.UUID;

public interface Entity {

    UUID getUuid();

    Instant getCreatedDate();

}
