//package br.com.imd.pdse.monitorando.configuration;
//
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//
//public class RoutingDataSource extends AbstractRoutingDataSource {
//    private static final ThreadLocal<Route> CTX = new ThreadLocal<>();
//
//
//    public enum Route {
//        WRITE("write"), READ("read");
//
//        private final String prefix;
//
//        Route(final String prefix) {
//            this.prefix = prefix;
//        }
//
//        public String getPrefix() {
//            return prefix;
//        }
//    }
//
//    public static void clearReadRoute() {
//        CTX.remove();
//    }
//
//    public static void setReadRoute() {
//        CTX.set(Route.READ);
//    }
//
//    public static void setWriteRoute() {
//        CTX.set(Route.WRITE);
//    }
//
//    @Override
//    protected Object determineCurrentLookupKey() {
//        return CTX.get();
//    }
//}