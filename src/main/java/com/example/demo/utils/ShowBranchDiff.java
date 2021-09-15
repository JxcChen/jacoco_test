package com.example.demo.utils;

/**
 * @author JJJJ
 * @Title:
 * @date 2021/9/15下午8:47
 * @Description: 展示不同分支的差异信息
 */


import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ShowBranchDiff {
    public static String getBranchDiff() throws Exception {
        StringBuffer sb = new StringBuffer();
        try (Repository repository = CookbookHelper.openJGitCookbookRepository()) {
            try (Git git = new Git(repository)) {
                if(repository.exactRef("refs/heads/master") == null) {
                    // first we need to ensure that the remote branch is visible locally
                    Ref ref = git.branchCreate().setName("dev").setStartPoint("dev").call();

                    System.out.println("Created local testbranch with ref: " + ref);
                }

                // the diff works on TreeIterators, we prepare two for the two branches
                AbstractTreeIterator oldTreeParser = prepareTreeParser(repository, "refs/heads/dev");
                AbstractTreeIterator newTreeParser = prepareTreeParser(repository, "refs/heads/master");


                ByteArrayOutputStream out = new ByteArrayOutputStream();
                DiffFormatter df = new DiffFormatter(out);
                df.setRepository(git.getRepository());
                // then the procelain diff-command returns a list of diff entries
                List<DiffEntry> diff = git.diff().setOldTree(oldTreeParser).setNewTree(newTreeParser).call();

                for (DiffEntry entry : diff) {
                    System.out.println("Entry: " + entry);
                    df.format(entry);
                    String diffText = out.toString("UTF-8");
                    sb.append(diffText);
                }

            }
        }
        return sb.toString();
    }

    private static AbstractTreeIterator prepareTreeParser(Repository repository, String ref) throws IOException {
        // from the commit we can build the tree which allows us to construct the TreeParser
        Ref head = repository.exactRef(ref);
        try (RevWalk walk = new RevWalk(repository)) {
            RevCommit commit = walk.parseCommit(head.getObjectId());
            RevTree tree = walk.parseTree(commit.getTree().getId());

            CanonicalTreeParser treeParser = new CanonicalTreeParser();
            try (ObjectReader reader = repository.newObjectReader()) {
                treeParser.reset(reader, tree.getId());
            }

            walk.dispose();

            return treeParser;
        }
    }
}
