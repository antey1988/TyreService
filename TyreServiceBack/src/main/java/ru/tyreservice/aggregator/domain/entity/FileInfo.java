package ru.tyreservice.aggregator.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
//@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String storageFileName;
    private String originalFileName;
    private String type;
    private Long size;
    private String url;

    @OneToOne
    @JoinColumn(name = "partner_id")
    private Partner partnerPhoto;

    @OneToOne
    @JoinColumn(name = "service_id")
    private Partner servicePhoto;
}