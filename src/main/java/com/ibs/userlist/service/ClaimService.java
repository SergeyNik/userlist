package com.ibs.userlist.service;

import com.ibs.userlist.model.Claim;
import com.ibs.userlist.model.ClaimFrom;
import com.ibs.userlist.model.ClaimStatus;
import com.ibs.userlist.model.ClaimTo;

import java.util.List;

public interface ClaimService {

    /**
     * Получить заявки по заданному количеству
     *
     * @param page       Номер страницы
     * @param size       Количество записей
     * @return Список заявок на страницу
     */
    List<Claim> getClaimsByCount(int page, int size);

    /**
     * Удалить заявку по id
     *
     * @param id       id заявки
     */
    void deleteById(long id);

    /**
     * Обновить заявку
     *
     * @param id            id заявки
     * @param updated       Новые данные заявки
     * @return Обновленная заявка
     */
    Claim update(long id, Claim updated);

    /**
     * Создать новую заявку
     *
     * @param claim     сформированная заявка
     * @return Новая заявка
     */
    Claim create(Claim claim);

    /**
     * Получить заявку по id
     *
     * @param id     id заявки
     * @return Найденная заявка
     */
    Claim getById(long id);

    /**
     * Получить список получателей заявки
     *
     * @return Список получателей
     */
    List<ClaimTo> getAllClaimTo();

    /**
     * Получить список отправителей заявки
     *
     * @return Список отправителей
     */
    List<ClaimFrom> getAllClaimFrom();

    /**
     * Получить список статусов заявки
     *
     * @return Список статусов
     */
    List<ClaimStatus> getAllClaimStatus();

    /**
     * Получить количество заявок
     *
     * @return Количество заявок
     */
    long getClaimsQuantity();
}
