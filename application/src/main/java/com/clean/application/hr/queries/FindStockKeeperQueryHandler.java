package com.clean.application.hr.queries;

import com.clean.application.hr.models.StockKeeperModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.hr.Employee;
import com.clean.domain.entity.hr.StockKeeper;
import com.clean.domain.entity.look.Category;
import com.clean.domain.entity.look.Location;
import com.clean.domain.entity.look.StockKeeperType;
import com.clean.persistence.hr.EmployeeRepository;
import com.clean.persistence.hr.StockKeeperRepository;
import com.clean.persistence.look.CategoryRepository;
import com.clean.persistence.look.LocationRepository;
import com.clean.persistence.look.StockKeeperTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class FindStockKeeperQueryHandler implements IRequestHandler<FindStockKeeperQuery, StockKeeperModel> {
   private StockKeeperRepository stockKeeperRepository;
   private EmployeeRepository employeeRepository;
   private StockKeeperTypeRepository stockKeeperTypeRepository;
   private CategoryRepository categoryRepository;
   private LocationRepository locationRepository;

   @Autowired
    FindStockKeeperQueryHandler(
            StockKeeperRepository stockKeeperRepository,
            EmployeeRepository employeeRepository,
            StockKeeperTypeRepository stockKeeperTypeRepository,
            CategoryRepository categoryRepository,
            LocationRepository locationRepository){
       this.stockKeeperRepository= stockKeeperRepository;
       this.employeeRepository = employeeRepository;
       this.stockKeeperTypeRepository = stockKeeperTypeRepository;
       this.categoryRepository = categoryRepository;
       this.locationRepository = locationRepository;
   }
    @Override
    public StockKeeperModel handle(FindStockKeeperQuery request) {
        StockKeeper stockKeeper = stockKeeperRepository.findById(request.getId()).orElseThrow(()->new RuntimeException("متعمد دریافت نگردید !"));
        Employee employee = employeeRepository.findById(stockKeeper.getEmployeeId()).get();
        StockKeeperType stockKeeperType = stockKeeperTypeRepository.findById(stockKeeper.getStockKeeperTypeId()).get();
        Category category = categoryRepository.findById(stockKeeper.getItemCategoryId()).get();
        
        StockKeeperModel stockKeeperModel =  StockKeeperModel.map(stockKeeper);
        stockKeeperModel.setName(employee.getName());
        stockKeeperModel.setLastName(employee.getLastName());
        stockKeeperModel.setFatherName(employee.getFatherName());
        stockKeeperModel.setStockKeeperTypeText(stockKeeperType.getName());
        stockKeeperModel.setItemCategoryText(category.getName());
        return  stockKeeperModel;
    }
}
