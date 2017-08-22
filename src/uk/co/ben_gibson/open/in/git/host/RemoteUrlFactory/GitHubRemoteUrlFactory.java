package uk.co.ben_gibson.open.in.git.host.RemoteUrlFactory;

import uk.co.ben_gibson.open.in.git.host.Git.*;
import uk.co.ben_gibson.open.in.git.host.Git.Exception.RemoteException;
import uk.co.ben_gibson.open.in.git.host.RemoteUrlFactory.Exception.RemoteUrlFactoryException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Creates URLs to Github.
 */
public class GitHubRemoteUrlFactory extends AbstractRemoteUrlFactory
{
    public URL createUrlToRemoteCommit(Remote remote, Commit commit, boolean forceSSL) throws RemoteUrlFactoryException, RemoteException
    {
        String path = String.format("commit/%s", commit.hash());

        return this.buildURL(remote, path, null, null, forceSSL);
    }

    public URL createUrlToRemotePath(Remote remote, Branch branch, String filePath, Integer lineNumber, boolean forceSSL) throws RemoteUrlFactoryException, RemoteException
    {
        String path     = String.format("blob/%s/%s", branch, filePath);
        String fragment = null;

        if (lineNumber != null) {
            fragment = String.format("L%d", lineNumber);
        }

        return this.buildURL(remote, path, null, fragment, forceSSL);
    }

    public boolean supports(RemoteHost host)
    {
        return host.gitHub();
    }
}
