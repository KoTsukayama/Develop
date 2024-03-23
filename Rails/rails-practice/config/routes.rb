Rails.application.routes.draw do
  get 'mypage', to: 'users#sssme'
  post 'login', to: 'sesson#create'
  delete 'logout', to: 'session#destroy'
  get 'home/index'
  get 'sessions/create'
  get 'sessions/destroy'
  # Define your application routes per the DSL in https://guides.rubyonrails.org/routing.html

  # Defines the root path route ("/")
  get 'boards', to: 'boards#index'
  get 'boards/new', to: 'boards#new'

  root 'home#index'
  resources :users, only: %i[new create]
  resources :boards
  resources :comments, only: %i[create destroy]
end

