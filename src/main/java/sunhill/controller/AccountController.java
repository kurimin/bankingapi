package sunhill.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sunhill.constants.RequestConstants;
import sunhill.constants.UriConstants;
import sunhill.model.Account;
import sunhill.model.AccountBalance;
import sunhill.model.Transaction;
import sunhill.service.IAccountService;

@RestController
@RequestMapping(produces = RequestConstants.CONTENT_TYPE_JSON)
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private IAccountService accountService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping(value = UriConstants.ACCOUNT_URI_ROOT, method = RequestMethod.POST)
    public Account createClientAccount(
            @PathVariable(value = UriConstants.CLIENT_PATHVAR) final Long clientId,
            @RequestBody final Account account) {

        return this.accountService.create(clientId, account);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = UriConstants.ACCOUNT_URI, method = RequestMethod.GET)
    public Account getClientAccount(
            @PathVariable(value = UriConstants.CLIENT_PATHVAR) final Long clientId,
            @PathVariable(value = UriConstants.ACCOUNT_PATHVAR) final Long accountId) {

        return this.accountService.get(clientId, accountId);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = UriConstants.ACCOUNT_URI_DEPOSIT, method = RequestMethod.PUT)
    public Account depositClientAccount(
            @PathVariable(value = UriConstants.CLIENT_PATHVAR) final Long clientId,
            @PathVariable(value = UriConstants.ACCOUNT_PATHVAR) final Long accountId,
            @RequestBody final Transaction transaction) {

        return this.accountService.deposit(clientId, accountId, transaction);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = UriConstants.ACCOUNT_URI_WITHDRAW, method = RequestMethod.PUT)
    public Account withdrawClientAccount(
            @PathVariable(value = UriConstants.CLIENT_PATHVAR) final Long clientId,
            @PathVariable(value = UriConstants.ACCOUNT_PATHVAR) final Long accountId,
            @RequestBody final Transaction transaction) {

        return this.accountService.withdraw(clientId, accountId, transaction);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = UriConstants.ACCOUNT_URI_BALANCE, method = RequestMethod.GET)
    public AccountBalance getClientAccountBalance(
            @PathVariable(value = UriConstants.CLIENT_PATHVAR) final Long clientId,
            @PathVariable(value = UriConstants.ACCOUNT_PATHVAR) final Long accountId) {

        return this.accountService.balance(clientId, accountId);
    }
}
