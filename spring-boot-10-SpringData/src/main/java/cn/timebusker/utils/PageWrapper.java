package cn.timebusker.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

/**
 * @Description:PageWrapper
 * @Author:Administrator
 * @Date2019/10/30 16:50
 **/
public class PageWrapper implements Pageable {

    @Override
    public int getPageNumber() {
        return 0;
    }

    @Override
    public int getPageSize() {
        return 0;
    }

    @Override
    public long getOffset() {
        return 0;
    }

    @Override
    public Sort getSort() {
        return null;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    @Override
    public boolean isPaged() {
        return false;
    }

    @Override
    public boolean isUnpaged() {
        return false;
    }

    @Override
    public Sort getSortOr(Sort sort) {
        return null;
    }

    @Override
    public Optional<Pageable> toOptional() {
        return Optional.empty();
    }
}
