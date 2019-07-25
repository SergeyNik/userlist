package com.ibs.userlist.service.impl;

import com.ibs.userlist.exceptions.NotFoundException;
import com.ibs.userlist.model.Claim;
import com.ibs.userlist.model.ClaimFrom;
import com.ibs.userlist.model.ClaimStatus;
import com.ibs.userlist.model.ClaimTo;
import com.ibs.userlist.repository.ClaimFromRepo;
import com.ibs.userlist.repository.ClaimRepository;
import com.ibs.userlist.repository.ClaimStatusRepo;
import com.ibs.userlist.repository.ClaimToRepo;
import com.ibs.userlist.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClaimServiceImpl implements ClaimService {

    private final ClaimRepository claimRepository;
    private final ClaimToRepo claimToRepo;
    private final ClaimFromRepo claimFromRepo;
    private final ClaimStatusRepo claimStatusRepo;

    @Autowired
    public ClaimServiceImpl(ClaimRepository claimRepository,
                            ClaimToRepo claimToRepo,
                            ClaimFromRepo claimFromRepo,
                            ClaimStatusRepo claimStatusRepo) {
        this.claimRepository = claimRepository;
        this.claimToRepo = claimToRepo;
        this.claimFromRepo = claimFromRepo;
        this.claimStatusRepo = claimStatusRepo;
    }

    public List<Claim> getClaimsByCount(int page, int size) {
        Page<Claim> claims = claimRepository.findAll(PageRequest.of(page, size));
        return claims.get().collect(Collectors.toList());
    }

    @Override
    public Claim getById(long id) {
        return claimRepository.findById(id).orElseThrow(() -> new NotFoundException("not"));
    }

    @Override
    @Transactional
    public Claim create(Claim claim) {
        return buildAndSaveClaim(claim, new Claim());
    }

    @Override
    @Transactional
    public Claim update(long id, Claim updated) {
        Claim claim = getById(id);
        return buildAndSaveClaim(updated, claim);
    }

    /**
     * Сформировать заявку
     *
     * @param updated       Новые данные заявки из dto
     * @param current       текущая или новая заявка для БД
     * @return Новая или обновленная сущность заявки
     */
    private Claim buildAndSaveClaim(Claim updated, Claim current) {
        ClaimTo claimTo = claimToRepo.findById(
                updated.getClaimTo().getId()).orElseThrow(() -> new NotFoundException("not"));
        ClaimFrom claimFrom = claimFromRepo.findById(
                updated.getClaimFrom().getId()).orElseThrow(() -> new NotFoundException("not"));
        ClaimStatus claimStatus = claimStatusRepo.findById(
                updated.getClaimStatus().getId()).orElseThrow(() -> new NotFoundException("not"));

        current.setName(updated.getName());
        current.setClaimTo(claimTo);
        current.setClaimFrom(claimFrom);
        current.setClaimStatus(claimStatus);

        return claimRepository.save(current);
    }

    @Override
    public void deleteById(long id) {
        claimRepository.deleteById(id);
    }

    @Override
    public List<ClaimTo> getAllClaimTo() {
        return claimToRepo.findAll();
    }

    @Override
    public List<ClaimFrom> getAllClaimFrom() {
        return claimFromRepo.findAll();
    }

    @Override
    public List<ClaimStatus> getAllClaimStatus() {
        return claimStatusRepo.findAll();
    }

    @Override
    public long getClaimsQuantity() {
        return claimRepository.count();
    }

}
