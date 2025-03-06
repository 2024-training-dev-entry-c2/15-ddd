package com.monopoly_managment.infa.mainservices.config;

import com.monopoly.monopoly_managment.application.banck_account.canceltransaction.CancelTransactionUseCase;
import com.monopoly.monopoly_managment.application.banck_account.createbankaccount.CreateBankAccountUseCase;
import com.monopoly.monopoly_managment.application.banck_account.novalidateionfounds.NoValidationFoundsUseCase;
import com.monopoly.monopoly_managment.application.banck_account.registertransaction.CreateTransactionUseCase;
import com.monopoly.monopoly_managment.application.banck_account.validatefounds.ValidateFoundsUseCase;
import com.monopoly.monopoly_managment.application.property.assignedowner.AssignOwnerUseCase;
import com.monopoly.monopoly_managment.application.property.cancelmortgage.CancelMortgageUseCase;
import com.monopoly.monopoly_managment.application.property.createproperty.CreatePropertyUseCase;
import com.monopoly.monopoly_managment.application.property.demolishimprovement.DemolishImprovementUseCase;
import com.monopoly.monopoly_managment.application.property.madeimprovement.MadeImprovementUseCase;
import com.monopoly.monopoly_managment.application.property.makemortgage.MakeMortgageUseCase;
import com.monopoly.monopoly_managment.application.property.modifyowner.ModifyOwnerUseCase;
import com.monopoly.monopoly_managment.application.property.removedowner.RemoveOwnerUseCase;
import com.monopoly.monopoly_managment.infa.mongo.adapters.MongoAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

  // region BankAccount
  @Bean
  public CancelTransactionUseCase cancelTransactionUseCase(MongoAdapter adapter) {
    return new CancelTransactionUseCase(adapter);
  }

  @Bean
  public CreateBankAccountUseCase createBankAccountUseCase(MongoAdapter adapter) {
    return new CreateBankAccountUseCase(adapter);
  }

  @Bean
  public NoValidationFoundsUseCase noValidationFoundsUseCase(MongoAdapter adapter) {
    return new NoValidationFoundsUseCase(adapter);
  }

  @Bean
  public CreateTransactionUseCase createTransactionUseCase(MongoAdapter adapter) {
    return new CreateTransactionUseCase(adapter);
  }

  @Bean
  public ValidateFoundsUseCase validateFoundsUseCase(MongoAdapter adapter) {
    return new ValidateFoundsUseCase(adapter);
  }
  // endregion

  // region Property
  @Bean
  public AssignOwnerUseCase assignOwnerUseCase(MongoAdapter adapter) {
    return new AssignOwnerUseCase(adapter);
  }

  @Bean
  public CancelMortgageUseCase cancelMortgageUseCase(MongoAdapter adapter) {
    return new CancelMortgageUseCase(adapter);
  }

  @Bean
  public CreatePropertyUseCase createPropertyUseCase(MongoAdapter adapter) {
    return new CreatePropertyUseCase(adapter);
  }

  @Bean
  public DemolishImprovementUseCase demolishImprovementUseCase(MongoAdapter adapter) {
    return new DemolishImprovementUseCase(adapter);
  }

  @Bean
  public MadeImprovementUseCase madeImprovementUseCase(MongoAdapter adapter) {
    return new MadeImprovementUseCase(adapter);
  }

  @Bean
  public MakeMortgageUseCase makeMortgageUseCase(MongoAdapter adapter) {
    return new MakeMortgageUseCase(adapter);
  }

  @Bean
  public ModifyOwnerUseCase modifyOwnerUseCase(MongoAdapter adapter) {
    return new ModifyOwnerUseCase(adapter);
  }

  @Bean
  public RemoveOwnerUseCase removeOwnerUseCase(MongoAdapter adapter) {
    return new RemoveOwnerUseCase(adapter);
  }

  // endregion

}
