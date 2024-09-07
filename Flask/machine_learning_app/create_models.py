import numpy as np
import pickle
from sklearn.datasets import make_classification, make_blobs
from sklearn.linear_model import LogisticRegression
from sklearn.decomposition import PCA
from sklearn.svm import SVR

# 教師あり学習モデルの作成
X, y = make_classification(n_samples=100, n_features=20, n_classes=2, random_state=42)
supervised_model = LogisticRegression()
supervised_model.fit(X, y)
with open('models/supervised_model.pkl', 'wb') as f:
    pickle.dump(supervised_model, f)

# 教師なし学習モデルの作成
X, _ = make_blobs(n_samples=100, n_features=2, centers=3, random_state=42)
unsupervised_model = PCA(n_components=2)
unsupervised_model.fit(X)
with open('models/unsupervised_model.pkl', 'wb') as f:
    pickle.dump(unsupervised_model, f)

# 強化学習モデルの作成
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

# 環境の設定
state_size = 5
action_size = 2
agent = QLearningAgent(state_size, action_size)

# シミュレーションの実行
num_episodes = 1000
for episode in range(num_episodes):
    state = np.random.randint(state_size)
    for step in range(10):
        action = agent.choose_action(state)
        reward = np.random.random()
        next_state = (state + 1) % state_size
        agent.update_q_table(state, action, reward, next_state)
        state = next_state

# モデルの保存
with open('models/reinforcement_model.pkl', 'wb') as f:
    pickle.dump(agent, f)
