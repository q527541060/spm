package com.sinictek.spm.advice;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import java.util.Collections;

/**
 * <p>
 *    事务管理
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-03-09
 */
@Aspect
@Configuration
public class TransactionAdviceConfig {

    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.sinictek.spm.service..*.*(..))";

    @Autowired

    private PlatformTransactionManager transactionManager;

    @Bean

    public TransactionInterceptor txAdvice() {

        RuleBasedTransactionAttribute txAttr_REQUIRED =new RuleBasedTransactionAttribute();

        txAttr_REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        txAttr_REQUIRED.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)) );


        DefaultTransactionAttribute txAttr_REQUIRED_READONLY = new DefaultTransactionAttribute();

        txAttr_REQUIRED_READONLY.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        txAttr_REQUIRED_READONLY.setReadOnly(true);

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();

        source.addTransactionalMethod("save*", txAttr_REQUIRED);

        source.addTransactionalMethod("del*", txAttr_REQUIRED);

        source.addTransactionalMethod("update*", txAttr_REQUIRED);

        source.addTransactionalMethod("exec*", txAttr_REQUIRED);

        source.addTransactionalMethod("set*", txAttr_REQUIRED);

        source.addTransactionalMethod("get*", txAttr_REQUIRED);

        source.addTransactionalMethod("remove*", txAttr_REQUIRED);

        source.addTransactionalMethod("save*", txAttr_REQUIRED);

        source.addTransactionalMethod("add*", txAttr_REQUIRED);

        source.addTransactionalMethod("create*", txAttr_REQUIRED);

        source.addTransactionalMethod("noTrans*", txAttr_REQUIRED);

        //read only  

        source.addTransactionalMethod("query*", txAttr_REQUIRED_READONLY);

        source.addTransactionalMethod("find*", txAttr_REQUIRED_READONLY);

        source.addTransactionalMethod("list*", txAttr_REQUIRED_READONLY);

        source.addTransactionalMethod("count*", txAttr_REQUIRED_READONLY);

        source.addTransactionalMethod("is*", txAttr_REQUIRED_READONLY);

        return new TransactionInterceptor(transactionManager, source);

    }


    @Bean

    public Advisor txAdviceAdvisor() {

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);

        return new DefaultPointcutAdvisor(pointcut, txAdvice());

    }

}  