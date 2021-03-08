package com.seizosystems.bugtracker.web.rest;

import com.seizosystems.bugtracker.domain.Attachment;
import com.seizosystems.bugtracker.repository.AttachmentRepository;
import com.seizosystems.bugtracker.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.seizosystems.bugtracker.domain.Attachment}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AttachmentResource {

    private final Logger log = LoggerFactory.getLogger(AttachmentResource.class);

    private static final String ENTITY_NAME = "attachment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AttachmentRepository attachmentRepository;

    public AttachmentResource(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    /**
     * {@code POST  /attachments} : Create a new attachment.
     *
     * @param attachment the attachment to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new attachment, or with status {@code 400 (Bad Request)} if the attachment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/attachments")
    public ResponseEntity<Attachment> createAttachment(@Valid @RequestBody Attachment attachment) throws URISyntaxException {
        log.debug("REST request to save Attachment : {}", attachment);
        if (attachment.getId() != null) {
            throw new BadRequestAlertException("A new attachment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Attachment result = attachmentRepository.save(attachment);
        return ResponseEntity.created(new URI("/api/attachments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /attachments} : Updates an existing attachment.
     *
     * @param attachment the attachment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated attachment,
     * or with status {@code 400 (Bad Request)} if the attachment is not valid,
     * or with status {@code 500 (Internal Server Error)} if the attachment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/attachments")
    public ResponseEntity<Attachment> updateAttachment(@Valid @RequestBody Attachment attachment) throws URISyntaxException {
        log.debug("REST request to update Attachment : {}", attachment);
        if (attachment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Attachment result = attachmentRepository.save(attachment);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, attachment.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /attachments} : get all the attachments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of attachments in body.
     */
    @GetMapping("/attachments")
    public List<Attachment> getAllAttachments() {
        log.debug("REST request to get all Attachments");
        return attachmentRepository.findAll();
    }

    /**
     * {@code GET  /attachments/:id} : get the "id" attachment.
     *
     * @param id the id of the attachment to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the attachment, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/attachments/{id}")
    public ResponseEntity<Attachment> getAttachment(@PathVariable Long id) {
        log.debug("REST request to get Attachment : {}", id);
        Optional<Attachment> attachment = attachmentRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(attachment);
    }

    /**
     * {@code DELETE  /attachments/:id} : delete the "id" attachment.
     *
     * @param id the id of the attachment to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/attachments/{id}")
    public ResponseEntity<Void> deleteAttachment(@PathVariable Long id) {
        log.debug("REST request to delete Attachment : {}", id);
        attachmentRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
