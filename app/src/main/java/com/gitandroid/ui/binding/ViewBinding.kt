package com.gitandroid.ui.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.core_model.EventType
import com.core_model.GitEvents

@BindingAdapter("image")
fun ImageView.setImage(url: String) {
    load(url){
        transformations(CircleCropTransformation())
    }
}

@BindingAdapter("imageId")
fun ImageView.setImage(imageId: Int) {
    setImageResource(imageId)
}

@BindingAdapter("textByEvent")
fun TextView.setTextByEvent(event: EventType) {
    text = context.getString(event.event)
    append(when(event) {
        EventType.WatchEvent -> " a repository"
        EventType.CreateEvent -> " a repository"
        EventType.CommitCommentEvent -> ""
        EventType.DownloadEvent -> ""
        EventType.FollowEvent -> ""
        EventType.ForkEvent -> " a repository"
        EventType.GistEvent -> ""
        EventType.GollumEvent -> ""
        EventType.IssueCommentEvent -> ""
        EventType.IssuesEvent -> ""
        EventType.MemberEvent -> ""
        EventType.PublicEvent -> ""
        EventType.PullRequestEvent -> ""
        EventType.PullRequestReviewCommentEvent -> ""
        EventType.PullRequestReviewEvent -> ""
        EventType.RepositoryEvent -> ""
        EventType.PushEvent -> ""
        EventType.TeamAddEvent -> ""
        EventType.DeleteEvent -> ""
        EventType.ReleaseEvent -> ""
        EventType.ForkApplyEvent -> ""
        EventType.OrgBlockEvent -> ""
        EventType.ProjectCardEvent -> ""
        EventType.ProjectColumnEvent -> ""
        EventType.OrganizationEvent -> ""
        EventType.ProjectEvent -> ""
    })
}

@BindingAdapter("event_card_title")
fun TextView.eventCardTitle(event: GitEvents) {
    text = when(event.type) {
        EventType.WatchEvent -> event.repo.name
        EventType.CreateEvent -> event.repo.name
        EventType.ForkEvent -> event.payload.forkee.full_name
        else -> event.repo.name
    }
}

@BindingAdapter("repo_stars")
fun TextView.setRepoStars(event: GitEvents) {
    text = when(event.type) {
        EventType.WatchEvent -> event.repo.stargazers_count.toString()
        EventType.CreateEvent -> event.repo.stargazers_count.toString()
        EventType.ForkEvent -> event.payload.forkee.stargazers_count.toString()
        else -> ""
    }
}

@BindingAdapter("repo_language")
fun TextView.setRepoLanguage(event: GitEvents) {
    text = when(event.type) {
        EventType.WatchEvent -> event.repo.language
        EventType.CreateEvent -> event.repo.language
        EventType.ForkEvent -> event.payload.forkee.language
        else -> ""
    }
}

@BindingAdapter("repo_description")
fun TextView.setRepoDescription(event: GitEvents) {
    text = when(event.type) {
        EventType.WatchEvent -> event.repo.description
        EventType.CreateEvent -> event.repo.description
        EventType.ForkEvent -> event.payload.forkee.description
        else -> ""
    }
}