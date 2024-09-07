import numpy as np
import pickle
from flask import Flask, render_template, request  # requestを追加


app = Flask(__name__)

class QLearningAgent:
    def __init__(self, state_size, action_size):
        self.state_size = state_size
        self.action_size = action_size
        self.q_table = np.zeros((state_size, action_size))
        self.learning_rate = 0.1
        self.discount_factor = 0.9
        self.exploration_rate = 1.0
        self.exploration_decay = 0.995
        self.min_exploration_rate = 0.01

    def choose_action(self, state):
        if np.random.rand() < self.exploration_rate:
            return np.random.randint(self.action_size)
        else:
            return np.argmax(self.q_table[state])

    def update_q_table(self, state, action, reward, next_state):
        best_next_action = np.argmax(self.q_table[next_state])
        td_target = reward + self.discount_factor * self.q_table[next_state, best_next_action]
        td_error = td_target - self.q_table[state, action]
        self.q_table[state, action] += self.learning_rate * td_error
        self.exploration_rate = max(self.min_exploration_rate, self.exploration_rate * self.exploration_decay)

# モデルの読み込み
def load_models():
    with open('models/supervised_model.pkl', 'rb') as f:
        supervised_model = pickle.load(f)
    with open('models/unsupervised_model.pkl', 'rb') as f:
        unsupervised_model = pickle.load(f)
    with open('models/reinforcement_model.pkl', 'rb') as f:
        reinforcement_model = pickle.load(f)
    return supervised_model, unsupervised_model, reinforcement_model

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/reinforcement', methods=['GET'])
def reinforcement():
    _, _, reinforcement_model = load_models()

    # ここで強化学習のエピソードごとの報酬や進捗を計算または取得します
    rewards = [10, 20, 15, 30]  # これはサンプルデータです

    return render_template('reinforcement.html', rewards=rewards)

@app.route('/supervised', methods=['GET', 'POST'])
def supervised():
    if request.method == 'POST':
        # ここにPOSTリクエストの処理
        data = request.form.get('some_data', '')  # フォームからデータを取得（キー 'some_data' の例）
        # データの処理など
        return render_template('supervised.html', data=data)

    return render_template('supervised.html')

@app.route('/unsupervised', methods=['GET', 'POST'])
def unsupervised():
    if request.method == 'POST':
        # POSTリクエストの処理をここに追加
        # 例えば、フォームデータの取得やモデルへの入力など
        return render_template('unsupervised.html')
    return render_template('unsupervised.html')
if __name__ == '__main__':
    app.run(debug=True)
