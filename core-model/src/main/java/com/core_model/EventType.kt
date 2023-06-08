package com.core_model

import com.core_domain.R

enum class EventType(val event: Int) {
    WatchEvent(R.string.starred),
    CreateEvent(R.string.created_repo),
    CommitCommentEvent(R.string.commented_on_commit),
    DownloadEvent(R.string.starred),
    FollowEvent(R.string.followed),
    ForkEvent(R.string.forked),
    GistEvent(R.string.created_gist),
    GollumEvent(R.string.gollum),
    IssueCommentEvent(R.string.commented_on_issue),
    IssuesEvent(R.string.created_issue),
    MemberEvent(R.string.member),
    PublicEvent(R.string.public_event),
    PullRequestEvent(R.string.pull_request),
    PullRequestReviewCommentEvent(R.string.pr_comment_review),
    PullRequestReviewEvent(R.string.pr_review_event),
    RepositoryEvent(R.string.repo_event),
    PushEvent(R.string.pushed),
    TeamAddEvent(R.string.team_event),
    DeleteEvent(R.string.deleted),
    ReleaseEvent(R.string.released),
    ForkApplyEvent(R.string.forked),
    OrgBlockEvent(R.string.organization_event),
    ProjectCardEvent(R.string.card_event),
    ProjectColumnEvent(R.string.project_event),
    OrganizationEvent(R.string.organization_event),
    ProjectEvent(R.string.project_event);
}