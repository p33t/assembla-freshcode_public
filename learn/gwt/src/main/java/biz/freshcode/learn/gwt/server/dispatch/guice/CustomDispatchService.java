package biz.freshcode.learn.gwt.server.dispatch.guice;

import biz.freshcode.learn.gwt2.mod2.shared.downloadable.DownloadResult;
import com.gwtplatform.dispatch.rpc.server.Dispatch;
import com.gwtplatform.dispatch.rpc.server.RequestProvider;
import com.gwtplatform.dispatch.rpc.server.guice.DispatchServiceImpl;
import com.gwtplatform.dispatch.rpc.shared.Action;
import com.gwtplatform.dispatch.rpc.shared.Result;
import com.gwtplatform.dispatch.rpc.shared.ServiceException;
import com.gwtplatform.dispatch.shared.ActionException;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@Singleton
public class CustomDispatchService extends DispatchServiceImpl {
    @Inject
    public CustomDispatchService(Logger logger, Dispatch dispatch, RequestProvider requestProvider) {
        super(logger, dispatch, requestProvider);
    }

    @Override
    public Result execute(String cookieSentByRPC, Action<?> action) throws ActionException, ServiceException {
        Result result = super.execute(cookieSentByRPC, action);
        /////////////////////////////////////////////
        // THIS DOES NOT WORK... CLIENT DOESN'T READ HEADERS
        if (result instanceof DownloadResult) {
            DownloadResult dr = (DownloadResult) result;
            String fileName = dr.getFileName();
            HttpServletResponse response = getThreadLocalResponse();
            response.setContentType("application/octet-stream");
            // NOTE: Need quotes around filename to cater to commas.
            // Otherwise Chrome gets ERR_RESPONSE_HEADERS_MULTIPLE_CONTENT_DISPOSITION
            // Also, Firefox needs quotes for filenames that have spaces
            response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName.replace('"', '_') + "\"");

            PrintWriter write;
            try {
                write = response.getWriter();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            while (dr.getLines().hasNext()) {
                write.write(dr.getLines().next());
                write.write(dr.getLineSeparator());
            }
            write.close();
//            throw new ResultDownloadedException();
        }
        return result;
    }
}
