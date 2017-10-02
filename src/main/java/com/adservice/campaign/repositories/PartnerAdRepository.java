package com.adservice.campaign.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adservice.campaign.domain.PartnerAd;


public interface PartnerAdRepository extends JpaRepository<PartnerAd, String> {

}
