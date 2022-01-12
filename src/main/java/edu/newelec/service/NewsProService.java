package edu.newelec.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.newelec.domain.NewsPro;

public interface NewsProService extends IService<NewsPro> {

    Integer updateByIdInModel(NewsPro newsPro);
    String getNameByProId(Integer id);
    IPage<NewsPro> showNewsPro(Long currentPage, Long size);

}
