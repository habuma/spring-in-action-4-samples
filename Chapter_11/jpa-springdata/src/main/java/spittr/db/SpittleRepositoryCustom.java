package spittr.db;

import java.util.List;

import spittr.domain.Spittle;

public interface SpittleRepositoryCustom {

  List<Spittle> findRecent();

  List<Spittle> findRecent(int count);

}