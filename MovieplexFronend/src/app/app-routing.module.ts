import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { SearchComponent } from './search/search.component';
import { LogoutComponent } from './logout/logout.component';
import { MoviesComponent } from './movies/movies.component';
import { FooterComponent } from './footer/footer.component';
import { FavouriteComponent } from './favourite/favourite.component';
import { ProfileComponent } from './profile/profile.component';
import { HomeComponent } from './home/home.component';
import { LoginGuard } from './guards/login.guard';
import { AboutComponent } from './about/about.component';
import { ContactComponent } from './contact/contact.component';

const routes: Routes = [
  { path : "", component: DashboardComponent},
  { path : "home", component : HomeComponent },
  { path : "login", component: LoginComponent},
  { path : "signup", component: SignupComponent},
  { path : "logout", component : LogoutComponent},
  { path : "search/:movieName", component : SearchComponent},
  { path : "movies/:id", component : MoviesComponent, canActivate:[LoginGuard]},
  { path : "fav", component : FavouriteComponent, canActivate:[LoginGuard]},
  { path : "profile", component : ProfileComponent, canActivate:[LoginGuard]},
  { path : "about", component : AboutComponent, canActivate:[LoginGuard]},
  { path : "contact", component : ContactComponent, canActivate:[LoginGuard]},
  { path : "footer", component : FooterComponent},
  { path : "pagenotfound", component: PagenotfoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
