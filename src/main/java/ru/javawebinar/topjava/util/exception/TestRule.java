package ru.javawebinar.topjava.util.exception;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: Alk
 * Date: 24.03.15
 * Time: 12:10
 * To change this template use File | Settings | File Templates.
 */
public class TestRule implements MethodRule {


    private final org.junit.rules.ExpectedException delegate = org.junit.rules.ExpectedException.none();

    public static TestRule none(){
        return new TestRule();
    }

    private TestRule() {
    }



    @Override
    public Statement apply(Statement base, FrameworkMethod method, Object target) {
        return delegate.apply(base,null);
    }

    public void expectAssertionError(String message) {
        expect(AssertionError.class);
        expectMessage(message);
    }
      public void notFound(String message){
        expect(NotFoundException.class);
        expectMessage("test");
}

    public void expectNullPointerException(String message) {
        expect(NullPointerException.class);
        expectMessage(message);
    }
    public void expect(Throwable error) {
        expect(error.getClass());
        expectMessage(error.getMessage());
    }

    public void expect(Class<? extends Throwable> type) {
        delegate.expect(type);
    }

    public void expectMessage(String message) {
        delegate.expectMessage(message);
    }
}
