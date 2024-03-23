class BoardsController < ApplicationController
    def index
    end

    def new
        # モデルのクラスを使う
        @board = Board.new

        # debug
        # binding.pry
    end
end
